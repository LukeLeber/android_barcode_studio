/**
 * This file is protected under the KILLGPL.
 * For more information visit https://github.com/LukeLeber/KILLGPL
 *
 * Copyright Luke A. Leber <LukeLeber@gmail.com> 8.10.2014
 *
 */

package com.lukeleber.barcodestudio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public final class Sequence
        implements Iterable<Module>
{
    private final List<Module> modules;

    public Sequence(Sequence rhs)
    {
        this.modules = new ArrayList<Module>();
        this.modules.addAll(rhs.modules);
    }

    public Sequence()
    {
        this.modules = new ArrayList<Module>();
    }

    @SafeVarargs
    public Sequence(Module... modules)
    {
        this.modules = new ArrayList<Module>(Arrays.asList(modules));
    }

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

    Sequence(Collection<Module> modules)
    {
        this.modules = new ArrayList<Module>(modules);
    }

    public Sequence append(Sequence sequence)
    {
        this.modules.addAll(sequence.modules);
        return this;
    }

    public Sequence append(Module module)
    {
        this.modules.add(module);
        return this;
    }

    public Sequence slice(int begin, int end)
    {
        return new Sequence(this.modules.subList(begin, end));
    }

    public int size()
    {
        return this.modules.size();
    }

    public void add(Sequence sequence)
    {
        for (Module m : sequence)
        {
            this.modules.add(m);
        }
    }

    @Override
    public Iterator<Module> iterator()
    {
        return modules.iterator();
    }

}
