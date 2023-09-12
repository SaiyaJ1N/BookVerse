package com.onlinebookshop.dto.book;

import com.onlinebookshop.dto.SearchParameters;

public record BookSearchParameters(String[] authors, String[] prices) implements SearchParameters {
}
