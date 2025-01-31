package net.leawind.mc.util.math.decisionmap.impl;


import net.leawind.mc.util.math.decisionmap.api.DecisionFactor;
import org.jetbrains.annotations.NotNull;

import java.util.function.BooleanSupplier;

public class DecisionFactorImpl implements DecisionFactor {
	private final @NotNull BooleanSupplier getter;
	protected              int             index;
	private                boolean         value = false;
	private                String          name  = "unnamed";

	public DecisionFactorImpl (@NotNull BooleanSupplier getter) {
		this.getter = getter;
	}

	@Override
	public @NotNull DecisionFactor update () {
		value = getter.getAsBoolean();
		return this;
	}

	@Override
	public boolean get () {
		return value;
	}

	@Override
	public @NotNull String getName () {
		return name;
	}

	@Override
	public void setName (String name) {
		this.name = name;
	}

	@Override
	public void setIndex (int index) {
		this.index = index;
	}

	@Override
	public int index () {
		return index;
	}
}
