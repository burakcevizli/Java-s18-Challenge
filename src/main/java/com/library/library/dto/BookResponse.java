package com.library.library.dto;

public record BookResponse(String bookName , AuthorResponse authorResponse,CategoryResponse categoryResponse) {
}
