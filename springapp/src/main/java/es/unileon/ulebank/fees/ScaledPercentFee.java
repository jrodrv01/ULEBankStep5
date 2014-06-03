package es.unileon.ulebank.fees;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A more complex fee which applies a variable percentage depending on the
 * amount to tax. A minimum is always added.
 *
 * @author roobre
 */
public class ScaledPercentFee implements FeeStrategy {

    private ArrayList<FeeStep> steps;

    /**
     * Minimum value which is always added to the total fee.
     */
    private double minimum;

    public ScaledPercentFee(double minimum) throws InvalidFeeException {
        if (minimum < 0) {
            throw new InvalidFeeException();
        }

        this.steps = new ArrayList<FeeStep>();
        this.minimum = minimum;
    }

    public void addStep(FeeStep step) throws CrossedStepException {
        if (this.steps.size() > 0) {
            Iterator<FeeStep> it = this.steps.iterator();
            FeeStep fs;

            do {
                fs = it.next();
                if (fs.collides(step)) {
                    throw new CrossedStepException(fs, step);
                }
            } while (it.hasNext());
        }

        this.steps.add(step);
    }

    /**
     * Returns the fee that should be applied to the given amount.
     *
     * @param value
     * @return The fee that should be applied to the given amount
     */
    public double getFee(double value) {
        boolean match;
        FeeStep fs = null;

        if (this.steps.size() > 0) {
            Iterator<FeeStep> it = this.steps.iterator();
            do {
                fs = it.next();
                match = fs.wraps(value);
            } while (!match && it.hasNext());

            return value * fs.getFee() + this.minimum;
        } else {
            return this.minimum;
        }
    }
  

	@Override
	public void setFee(double newFee) {
this.minimum= newFee;		
	}
}
