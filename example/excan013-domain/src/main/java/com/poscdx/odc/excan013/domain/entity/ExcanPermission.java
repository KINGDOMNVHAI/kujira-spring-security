package com.poscdx.odc.excan013.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExcanPermission
{
    private int id;
    private String name;
    private String description;
    private int group;
}
