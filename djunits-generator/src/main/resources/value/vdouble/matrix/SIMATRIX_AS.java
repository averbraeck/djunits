    /**
     * Return the current matrix as a %type% matrix.
     * @return %Type%Matrix; the current matrix as a %type% matrix
     */
    public final %Type%Matrix as%Type%()
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(%Type%Unit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to %Type%Matrix", this.toString());
        return new %Type%Matrix(this.data, %Type%Unit.SI);
    }

    /**
     * Return the current matrix as a %type% matrix, and provide a display unit.
     * @param displayUnit the unit in which the value will be displayed
     * @return %Type%Matrix; the current matrix as a %type% matrix
     */
    public final %Type%Matrix as%Type%(final %Type%Unit displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(%Type%Unit.BASE.getSiDimensions())),
                UnitRuntimeException.class, "cannot cast %s to %Type%Matrix", this.toString());
        %Type%Matrix result = new %Type%Matrix(this.data, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }
