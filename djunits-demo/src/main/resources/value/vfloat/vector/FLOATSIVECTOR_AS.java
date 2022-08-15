    /**
     * Return the current vector as a %type% vector.
     * @return Float%Type%Vector; the current vector as a %type% vector
     */
    public final Float%Type%Vector as%Type%()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(%Type%Unit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to Float%Type%Vector", this.toString());
        return new Float%Type%Vector(this.data, %Type%Unit.SI);
    }

    /**
     * Return the current vector as a %type% vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return Float%Type%Vector; the current vector as a %type% vector
     */
    public final Float%Type%Vector as%Type%(final %Type%Unit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(%Type%Unit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to Float%Type%Vector", this.toString());
        Float%Type%Vector result = new Float%Type%Vector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }
