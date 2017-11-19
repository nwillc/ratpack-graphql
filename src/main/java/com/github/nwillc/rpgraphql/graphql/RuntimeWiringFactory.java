/*
 * Copyright $date.year nwillc@gmail.com
 *
 * Permission to use, copy, modify, and/or distribute this software for any purpose with or without fee is hereby granted, provided that the above copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */

package com.github.nwillc.rpgraphql.graphql;

import com.github.nwillc.rpgraphql.graphql.fetchers.CompanyDelete;
import com.github.nwillc.rpgraphql.graphql.fetchers.CompanyMutation;
import com.github.nwillc.rpgraphql.graphql.fetchers.CompanyQuery;
import com.github.nwillc.rpgraphql.model.Company;
import graphql.schema.idl.RuntimeWiring;

import java.util.Map;

public class RuntimeWiringFactory {
    public static RuntimeWiring getRuntimeWiring(final Map<String, Company> companies) {
        return RuntimeWiring.newRuntimeWiring()
                .type("QueryType", wiring -> wiring
                        .dataFetcher("company", new CompanyQuery(companies))
                )
                .type("MutationType", wiring -> wiring
                        .dataFetcher("company", new CompanyMutation(companies))
                        .dataFetcher("companyDelete", new CompanyDelete(companies))
                )
                .build();
    }
}
