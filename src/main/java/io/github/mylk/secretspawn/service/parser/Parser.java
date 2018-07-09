package io.github.mylk.secretspawn.service.parser;

import io.github.mylk.secretspawn.model.Secret;

public interface Parser {
    Secret parse(String json, Secret secret);
}
