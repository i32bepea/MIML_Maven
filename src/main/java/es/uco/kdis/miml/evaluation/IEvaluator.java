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

package es.uco.kdis.miml.evaluation;

import es.uco.kdis.miml.classifiers.miml.IMIMLClassifier;

/**
 * Interface for run and evaluate a experiment.
 *
 * @param <T> the generic type of the evaluation generated by the experiment
 * 
 * @author Alvaro A. Belmonte
 * @author Eva Gibaja
 * @author Amelia Zafra
 * @version 20180630
 */
public interface IEvaluator<T> {

	/**
	 * Run experiment.
	 *
	 * @param classifier the classifier used in the experiment
	 */
	public void runExperiment(IMIMLClassifier classifier);

	/**
	 * Gets the evaluation generated by the experiment.
	 *
	 * @return the evaluation
	 */
	public T getEvaluation();
}
