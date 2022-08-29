package com.personal.revaturep2pokepostbe.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ability {
    private String name;
    private String url;
    private int slot;
    private boolean isHidden;
}
