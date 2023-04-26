package com.dbflixproject.dbfilx.dto.creator;

import com.dbflixproject.dbfilx.entity.creator.CreatorMovieConnectionEntity;
import com.dbflixproject.dbfilx.entity.enumfile.MovieRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieRoleDto {
    private Long seq;
    private String name;
    private MovieRole role;

    public MovieRoleDto(CreatorMovieConnectionEntity entity){
        this.seq = entity.getMovie().getMiSeq();
        this.name = entity.getMovie().getMiTitle();
        this.role = entity.getCmcRole();
    }
}
