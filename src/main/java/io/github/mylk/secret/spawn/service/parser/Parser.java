package io.github.mylk.secret.spawn.service.parser;

import io.github.mylk.secret.spawn.model.Secret;

public interface Parser {
    Secret parse(String json, Secret secret);
}
