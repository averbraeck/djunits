    /**
     * Return the current vector as a %type% vector.
     * @return %Type%Vector; the current vector as a %type% vector
     */
    public final %Type%Vector as%Type%()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(%Type%Unit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to %Type%Vector", this.toString());
        return new %Type%Vector(this.data, %Type%Unit.SI);
    }

    /**
     * Return the current vector as a %type% vector, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return %Type%Vector; the current vector as a %type% vector
     */
    public final %Type%Vector as%Type%(final %Type%Unit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(%Type%Unit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to %Type%Vector", this.toString());
        %Type%Vector result = new %Type%Vector(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }
