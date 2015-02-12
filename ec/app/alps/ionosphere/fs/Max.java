/*
  Copyright 2006 by Sean Luke
  Licensed under the Academic Free License version 3.0
  See the file "LICENSE" for more information
*/


package ec.app.alps.ionosphere.fs;

import ec.*;
import ec.app.alps.ionosphere.DoubleData;
import ec.gp.*;
import ec.util.*;

public class Max extends GPNode
    {
    public String toString() { return "max"; }

    public void checkConstraints(final EvolutionState state,
        final int tree,
        final GPIndividual typicalIndividual,
        final Parameter individualBase)
        {
        super.checkConstraints(state,tree,typicalIndividual,individualBase);
        if (children.length!=2)
            state.output.error("Incorrect number of children for node " + 
                toStringForError() + " at " +
                individualBase);
        }

    public void eval(final EvolutionState state,
        final int thread,
        final GPData input,
        final ADFStack stack,
        final GPIndividual individual,
        final Problem problem)
        {
        double result;
        DoubleData rd = ((DoubleData)(input));

        children[0].eval(state,thread,input,stack,individual,problem);
        result = rd.x;

        children[1].eval(state,thread,input,stack,individual,problem);
        //return maximum terminal
        rd.x = (result>rd.x)?result:rd.x;
        }
    }

