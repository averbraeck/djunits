    /**
     * Return the current scalar as a %type%.
     * @return Float%Type%; the current scalar as a %type%
     */
    public final Float%Type% as%Type%()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(%Type%Unit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to Float%Type%", this.toString());
        return new Float%Type%(getSI(), %Type%Unit.SI);
    }

    /**
     * Return the current scalar as a %type%, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return Float%Type%; the current scalar as a %type%
     */
    public final Float%Type% as%Type%(final %Type%Unit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(%Type%Unit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to Float%Type%", this.toString());
        Float%Type% result = new Float%Type%(getSI(), displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

