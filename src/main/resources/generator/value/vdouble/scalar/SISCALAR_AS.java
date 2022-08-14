    /**
     * Return the current scalar as a %type%.
     * @return %Type%; the current scalar as a %type%
     */
    public final %Type% as%Type%()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(%Type%Unit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to %Type%", this.toString());
        return new %Type%(getSI(), %Type%Unit.SI);
    }

    /**
     * Return the current scalar as a %type%, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return %Type%; the current scalar as a %type%
     */
    public final %Type% as%Type%(final %Type%Unit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(%Type%Unit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to %Type%", this.toString());
        %Type% result = new %Type%(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

