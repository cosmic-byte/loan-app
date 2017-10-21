package com.coderank.io.elasticrepo;

import com.coderank.io.dto.UserDto;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserRepositoryES extends ElasticsearchRepository<UserDto, Long> {
}
