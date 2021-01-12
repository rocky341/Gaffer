/*
 * Copyright 2016-2020 Crown Copyright
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

package uk.gov.gchq.gaffer.integration.impl;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import uk.gov.gchq.gaffer.integration.GafferTest;
import uk.gov.gchq.gaffer.integration.MultiStoreTest;
import uk.gov.gchq.gaffer.integration.extensions.GafferTestCase;
import uk.gov.gchq.gaffer.integration.factory.MapStoreGraphFactory;
import uk.gov.gchq.gaffer.integration.template.PartAggregationITTemplate;
import uk.gov.gchq.gaffer.operation.OperationException;
import uk.gov.gchq.gaffer.parquetstore.ParquetStore;
import uk.gov.gchq.gaffer.rest.factory.GraphFactory;

@MultiStoreTest
public class PartAggregationIT extends PartAggregationITTemplate {

    @Autowired
    private GraphFactory graphFactory;

    @BeforeEach
    public void resetRemoteProxyGraph() {
        if (graphFactory instanceof MapStoreGraphFactory) {
            ((MapStoreGraphFactory) graphFactory).reset(createSchema());
        } else {
            throw new RuntimeException("Expected the MapStoreGraph Factory to be injected");
        }
    }

    @Override
    @GafferTest(excludeStores = ParquetStore.class) // Known bug in the Parquet store
    public void shouldAggregateOnlyRequiredGroups(final GafferTestCase testcase) throws OperationException {
        super.shouldAggregateOnlyRequiredGroups(testcase);
    }
}
