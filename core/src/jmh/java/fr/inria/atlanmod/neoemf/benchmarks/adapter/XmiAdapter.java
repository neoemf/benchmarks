/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.adapter;

import fr.inria.atlanmod.neoemf.config.ImmutableConfig;

import org.atlanmod.commons.Preconditions;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import java.nio.file.Path;
import java.util.Collections;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An {@link Adapter} on top a an original XMI {@link Resource}.
 */
@AdapterName("xmi")
@ParametersAreNonnullByDefault
public class XmiAdapter extends AbstractAdapter {

    /**
     * Constructs a new {@code XmiAdapter}.
     */
    public XmiAdapter() {
        super("xmi", org.eclipse.gmt.modisco.java.emf.impl.JavaPackageImpl.class);
    }

    @Nonnull
    @Override
    public URI createUri(Path directory, String fileName) {
        return URI.createFileURI(directory.resolve(fileName + ".xmi").toFile().getAbsolutePath());
    }

    @Nonnull
    @Override
    public Resource create(URI uri) {
        final ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
        return resourceSet.createResource(uri);
    }

    @Nonnull
    @Override
    protected Map<String, ?> getOptions(ImmutableConfig config) {
        return Collections.emptyMap();
    }
}
