/*
 * Copyright $date.year nwillc@gmail.com
 *
 * Permission to use, copy, modify, and/or distribute this software for any purpose with or without fee is hereby granted, provided that the above copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */

package com.github.nwillc.rpgraphql.graphql.fetchers;

import com.github.nwillc.rpgraphql.model.Company;
import graphql.schema.DataFetcher;

import java.util.Map;

public abstract class CompanyFetcher<R> implements DataFetcher<R> {
    private final Map<String, Company> companies;

    public CompanyFetcher(Map<String, Company> companies) {
        this.companies = companies;
    }

    protected Map<String, Company> getCompanies() {
        return companies;
    }
}
