<<<<<<< HEAD
// This file is protected under the KILLGPL.
// For more information visit <insert_valid_link_to_killgpl_here>
// <p/>
// Copyright (c) Luke A. Leber <LukeLeber@gmail.com> 2014
//__________________________________________________________________________________________________

package com.lukeleber.barcodestudio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * A Sequence is a series of Modules that together form a barcode encoding
 */
public final class Sequence
        implements Iterable<Module>,
                   Serializable
{
    /// The underlying container
    private final List<Module> modules;

    /**
     * Default constructor (the resulting Sequence is empty)
     */
    public Sequence()
    {
        this.modules = new ArrayList<Module>();
    }

    /**
     * Internal Constructor - Optionally makes a read-only Sequence.  Any attempts to modify the
     * resulting Sequence will result in an {@link java.lang.UnsupportedOperationException}
     *
     * @param readOnly
     *         Should this Sequence be read-only?
     * @param firstOn
     *         Should the first Module be a bar or a space?
     * @param weights
     *         The weights of the subsequent Modules
     */
    @SuppressWarnings("unused")
    Sequence(boolean readOnly, boolean firstOn, int... weights)
    {
        List<Module> modules = new ArrayList<Module>();
        this.modules = readOnly ? Collections.unmodifiableList(modules) : modules;
        for (int weight : weights)
        {
            modules.add(new Module(firstOn, weight));
            firstOn = !firstOn;
        }
    }

    /**
     * Copy constructor
     *
     * @param rhs
     *         the Sequence to copy into this one
     */
    @SuppressWarnings("unused")
    public Sequence(Sequence rhs)
    {
        this.modules = new ArrayList<Module>();
        this.modules.addAll(rhs.modules);
    }

    /**
     * Constructs a Sequence from the provided modules
     *
     * @param modules
     *         the Modules to populate this Sequence with
     */
    public Sequence(Module... modules)
    {
        this.modules = new ArrayList<Module>(Arrays.asList(modules));
    }

    /**
     * Constructs a Sequence with the provided 'on' flag and weights
     *
     * @param firstOn
     *         Should the first Module be a bar or a space?
     * @param weights
     *         The weights of the subsequent Modules
     */
    @SuppressWarnings("unused")
    public Sequence(boolean firstOn, int... weights)
    {
        this.modules = new ArrayList<Module>();
        for (int weight : weights)
        {
            modules.add(new Module(firstOn, weight));
            firstOn = !firstOn;
        }
    }

    /**
     * Constructs a Sequence with the provided weights - with a bar as the first Module
     *
     * @param weights
     *         The weights of the subsequent Modules
     */
    public Sequence(int... weights)
    {
        this.modules = new ArrayList<Module>();
        boolean flag = true;
        for (int weight : weights)
        {
            modules.add(new Module(flag, weight));
            flag = !flag;
        }
    }

    /**
     * Constructs a Sequence from the provided Collection of Modules
     *
     * @param modules
     *         The collection of Modules to use
     */
    Sequence(Collection<Module> modules)
    {
        this.modules = new ArrayList<Module>(modules);
    }

    /**
     * Appends the provided Sequence onto this Sequence
     *
     * @param sequence
     *         the Sequence to append
     *
     * @return this
     */
    public Sequence append(Sequence sequence)
    {
        this.modules.addAll(sequence.modules);
        return this;
    }

    /**
     * Appends the provided Module onto this Sequence
     *
     * @param module
     *         the Module to append
     *
     * @return this
     */
    public Sequence append(Module module)
    {
        this.modules.add(module);
        return this;
    }

    /**
     * Retrieves a "sub-sequence" from the provided range
     *
     * @param begin
     *         the start position
     * @param end
     *         the end position
     *
     * @return the requested sub-sequence
     *
     * @throws java.lang.IndexOutOfBoundsException
     *         if either extent lies outside of the bounds of this Sequence
     */
    public Sequence slice(int begin, int end)
    {
        return new Sequence(this.modules.subList(begin, end));
    }

    /**
     * Retrieves the number of Modules in this Sequence
     *
     * @return the number of Modules in this Sequence
     */
    public int size()
    {
        return this.modules.size();
    }

    /**
     * Retrieves an Iterator to the underlying container
     *
     * @return an Iterator to the underlying container
=======
/**
 * This file is protected under the KILLGPL.
 * For more information visit https://github.com/LukeLeber/KILLGPL
 *
 * Copyright Luke A. Leber <LukeLeber@gmail.com> 8.10.2014
 *
 */

package com.lukeleber.barcodestudio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * A Sequence is a series of Modules that together form a barcode encoding
 */
public final class Sequence
        implements Iterable<Module>,
                   Serializable
{
    /// The underlying container
    private final List<Module> modules;

    /**
     * Default constructor (the resulting Sequence is empty)
     */
    public Sequence()
    {
        this.modules = new ArrayList<Module>();
    }

    /**
     * Internal Constructor - Optionally makes a read-only Sequence.  Any attempts to modify the
     * resulting Sequence will result in an {@link java.lang.UnsupportedOperationException}
     *
     * @param readOnly Should this Sequence be read-only?
     *
     * @param firstOn Should the first Module be a bar or a space?
     *
     * @param weights The weights of the subsequent Modules
     *
     */
    @SafeVarargs
    Sequence(boolean readOnly, boolean firstOn, int... weights)
    {
        List<Module> modules = new ArrayList<Module>();
        this.modules = readOnly ? Collections.unmodifiableList(modules) : modules;
        for (int weight : weights)
        {
            modules.add(new Module(firstOn, weight));
            firstOn = !firstOn;
        }
    }

    /**
     * Copy constructor
     *
     * @param rhs the Sequence to copy into this one
     *
     */
    public Sequence(Sequence rhs)
    {
        this.modules = new ArrayList<Module>();
        this.modules.addAll(rhs.modules);
    }

    /**
     * Constructs a Sequence from the provided modules
     *
     * @param modules the Modules to populate this Sequence with
     */
    @SafeVarargs
    public Sequence(Module... modules)
    {
        this.modules = new ArrayList<Module>(Arrays.asList(modules));
    }

    /**
     * Constructs a Sequence with the provided 'on' flag and weights
     *
     * @param firstOn Should the first Module be a bar or a space?
     * @param weights The weights of the subsequent Modules
     */
    @SafeVarargs
    public Sequence(boolean firstOn, int... weights)
    {
        this.modules = new ArrayList<Module>();
        for (int weight : weights)
        {
            modules.add(new Module(firstOn, weight));
            firstOn = !firstOn;
        }
    }

    /**
     * Constructs a Sequence with the provided weights - with a bar as the first Module
     *
     * @param weights The weights of the subsequent Modules
     *
     */
    @SafeVarargs
    public Sequence(int... weights)
    {
        this.modules = new ArrayList<Module>();
        boolean flag = true;
        for (int weight : weights)
        {
            modules.add(new Module(flag, weight));
            flag = !flag;
        }
    }

    /**
     * Constructs a Sequence from the provided Collection of Modules
     *
     * @param modules The collection of Modules to use
     *
     */
    Sequence(Collection<Module> modules)
    {
        this.modules = new ArrayList<Module>(modules);
    }

    /**
     * Appends the provided Sequence onto this Sequence
     *
     * @param sequence the Sequence to append
     *
     * @return this
     *
     */
    public Sequence append(Sequence sequence)
    {
        this.modules.addAll(sequence.modules);
        return this;
    }

    /**
     * Appends the provided Module onto this Sequence
     *
     * @param module the Module to append
     *
     * @return this
     *
     */
    public Sequence append(Module module)
    {
        this.modules.add(module);
        return this;
    }

    /**
     * Retrieves a "sub-sequence" from the provided range
     *
     * @param begin the start position
     *
     * @param end the end position
     *
     * @return the requested sub-sequence
     *
     * @throws java.lang.IndexOutOfBoundsException if either extent lies outside of the bounds of
     * this Sequence
     *
     */
    public Sequence slice(int begin, int end)
    {
        return new Sequence(this.modules.subList(begin, end));
    }

    /**
     * Retrieves the number of Modules in this Sequence
     *
     * @return the number of Modules in this Sequence
     *
     */
    public int size()
    {
        return this.modules.size();
    }

    /**
     * Retrieves an Iterator to the underlying container
     *
     * @return an Iterator to the underlying container
     *
>>>>>>> branch 'master' of https://github.com/LukeLeber/android_barcode_studio.git
     */
    @Override
    public Iterator<Module> iterator()
    {
        return modules.iterator();
    }
}
