package com.dbflixproject.dbfilx.dto.movie;

import com.dbflixproject.dbfilx.entity.enumfile.MovieRole;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieAddCreatorDto {
    @NotNull
    private Long creatorSeq;
    @NotNull
    private Long movieSeq;
    @NotNull
    private MovieRole role;
}
