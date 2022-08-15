    /**
     * Return the current matrix as a %type% matrix.
     * @return Float%Type%Matrix; the current matrix as a %type% matrix
     */
    public final Float%Type%Matrix as%Type%()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(%Type%Unit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to Float%Type%Matrix", this.toString());
        return new Float%Type%Matrix(this.data, %Type%Unit.SI);
    }

    /**
     * Return the current matrix as a %type% matrix, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return Float%Type%Matrix; the current matrix as a %type% matrix
     */
    public final Float%Type%Matrix as%Type%(final %Type%Unit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(%Type%Unit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to Float%Type%Matrix", this.toString());
        Float%Type%Matrix result = new Float%Type%Matrix(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }
