/*
 * Copyright 2016 Crown Copyright
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package gaffer.accumulostore.operation.impl;

import gaffer.accumulostore.utils.Pair;
import gaffer.commonutil.iterable.CloseableIterable;
import gaffer.data.element.Entity;
import gaffer.data.elementdefinition.view.View;
import gaffer.operation.AbstractGetOperation;
import gaffer.operation.GetOperation;
import gaffer.operation.data.ElementSeed;

/**
 * This returns all {@link gaffer.data.element.Entity}'s between the provided
 * {@link gaffer.operation.data.ElementSeed}s.
 */
public class GetEntitiesInRanges<SEED_TYPE extends Pair<? extends ElementSeed>> extends GetElementsInRanges<SEED_TYPE, Entity> {

    public GetEntitiesInRanges() {
    }

    public GetEntitiesInRanges(final Iterable<SEED_TYPE> seeds) {
        super(seeds);
    }

    public GetEntitiesInRanges(final View view) {
        super(view);
    }

    public GetEntitiesInRanges(final View view, final Iterable<SEED_TYPE> seeds) {
        super(view, seeds);
    }

    public GetEntitiesInRanges(final GetOperation<SEED_TYPE, Entity> operation) {
        super(operation);
    }

    @Override
    public boolean isIncludeEntities() {
        return true;
    }

    @Override
    public void setIncludeEntities(final boolean includeEntities) {
        if (!includeEntities) {
            throw new IllegalArgumentException(getClass().getSimpleName() + " requires entities to be included");
        }
    }

    @Override
    public IncludeEdgeType getIncludeEdges() {
        return IncludeEdgeType.NONE;
    }

    @Override
    public void setIncludeEdges(final IncludeEdgeType includeEdges) {
        if (IncludeEdgeType.NONE != includeEdges) {
            throw new IllegalArgumentException(getClass().getSimpleName() + " does not support including edges");
        }
    }

    public abstract static class BaseBuilder<SEED_TYPE extends Pair<? extends ElementSeed>, CHILD_CLASS extends BaseBuilder<SEED_TYPE, ?>>
            extends AbstractGetOperation.BaseBuilder<GetEntitiesInRanges<SEED_TYPE>, SEED_TYPE, CloseableIterable<Entity>, CHILD_CLASS> {
        public BaseBuilder() {
            super(new GetEntitiesInRanges<SEED_TYPE>());
        }
    }

    public static final class Builder<SEED_TYPE extends Pair<? extends ElementSeed>>
            extends BaseBuilder<SEED_TYPE, Builder<SEED_TYPE>> {

        @Override
        protected Builder<SEED_TYPE> self() {
            return this;
        }
    }
}
