/*
 * Copyright $date.year nwillc@gmail.com
 *
 * Permission to use, copy, modify, and/or distribute this software for any purpose with or without fee is hereby granted, provided that the above copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */

package com.github.nwillc.rpgraphql.handlers;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

@SuppressWarnings("unchecked")
public class GraphQLHandlerTest {
    private GraphQLHandler graphQLHandler;

    @Before
    public void setUp() throws Exception {
        graphQLHandler = new GraphQLHandler();
    }


    @Test
    public void testAddCompany() throws Exception {
        assertThat(graphQLHandler.getCompanies()).doesNotContainKeys("Acme");
        ExecutionInput executionInput = ExecutionInput.newExecutionInput()
                .query("mutation { company ( name: \"Acme\" revenue: 42) { name revenue }}")
                .build();

        assertThat(executionInput).isNotNull();

        final ExecutionResult result = graphQLHandler.getGraphql().execute(executionInput);
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isEmpty();

        Map data = result.getData();
        assertThat(data).containsKeys("company");
        data = (Map) data.get("company");
        assertThat(data).containsExactly(entry("name", "Acme"), entry("revenue", 42));
        assertThat(graphQLHandler.getCompanies()).containsKeys("Acme");
    }
}