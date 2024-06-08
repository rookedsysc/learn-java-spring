package org.i18ntest.jpajsonperformancemeasurement.domain;

import jakarta.persistence.*;
import org.i18ntest.jpajsonperformancemeasurement.common.JsonLongBooleanMapConverter;

import java.util.HashMap;

@Entity(name = "json_post")
public class JSONPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    @Convert(converter = JsonLongBooleanMapConverter.class)
    private HashMap<Long, Boolean> votes;
}
