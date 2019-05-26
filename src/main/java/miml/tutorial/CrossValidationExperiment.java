/*    This program is free software; you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation; either version 2 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program; if not, write to the Free Software
 *    Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */

package miml.tutorial;

import java.io.File;

import miml.classifiers.miml.lazy.MIMLkNN;
import miml.classifiers.miml.mimlTOmi.MIMLClassifierToMI;
import miml.classifiers.miml.mimlTOmi.MIMLLabelPowerset;
import miml.classifiers.miml.mimlTOml.MIMLClassifierToML;
import miml.core.ConfigParameters;
import miml.core.distance.MaximalHausdorff;
import miml.data.MIMLInstances;
import miml.evaluation.EvaluatorCV;
import miml.report.BaseMIMLReport;
import miml.transformation.mimlTOml.GeometricTransformation;
import mulan.classifier.lazy.DMLkNN;
import weka.classifiers.mi.SimpleMI;

/**
 * Class implementing an example of using cross-validation with the 3 different kinds of classifier. 
 * 
 * @author Alvaro A. Belmonte
 * @author Eva Gibaja
 * @author Amelia Zafra
 * @version 20190525
 *
 */
public class CrossValidationExperiment {
	public static void main(String[] args) throws Exception {
		
		ConfigParameters.setConfigFileName("EXAMPLE");
		ConfigParameters.setDataFileName("miml_birds.arff");
		
		String arffFileName = "data" + File.separator + "miml_birds.arff";
		String xmlFileName = "data" + File.separator + "miml_birds.xml";
		
		// Loads the dataset
		System.out.println("Loading the dataset...");
		MIMLInstances mimlDataSet = new MIMLInstances(arffFileName, xmlFileName);
		
		// MIML report
		BaseMIMLReport report = new BaseMIMLReport(null,"example.csv", true, true);
		
		// Cross-validation evaluator
		EvaluatorCV cv = new EvaluatorCV(mimlDataSet, 5);
		
		// Load classifiers
		System.out.println("Loading classifiers...");
		MIMLkNN mimlknn = new MIMLkNN(new MaximalHausdorff());
		MIMLClassifierToMI mimltomi = new MIMLClassifierToMI(new MIMLLabelPowerset(new SimpleMI()));
		MIMLClassifierToML mimltoml = new MIMLClassifierToML(new DMLkNN(), new GeometricTransformation());
		
		System.out.println("\n");
		
		System.out.println("-First example cross-validation using MIMLkNN:\n");
		cv.runExperiment(mimlknn);
		ConfigParameters.setAlgorirthmName("MIMLkNN");
		System.out.println(report.toString(cv) + "\n\n");

		System.out.println("-Second example cross-validation using MIMLtoMI transformation:\n");
		cv.runExperiment(mimltomi);
		ConfigParameters.setAlgorirthmName("MIMLClassiferToMI");
		System.out.println(report.toString(cv) + "\n\n");
		
		System.out.println("-Third example cross-validation using MIMLtoML transformation:\n");
		cv.runExperiment(mimltoml);
		ConfigParameters.setAlgorirthmName("MIMLClassiferToML");
		System.out.println(report.toString(cv));
		
		
		

		System.out.println("The program has finished.");
	}
}
