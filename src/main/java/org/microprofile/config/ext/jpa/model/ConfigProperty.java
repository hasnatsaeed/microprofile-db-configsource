package org.microprofile.config.ext.jpa.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@NamedQueries({
        @NamedQuery(name = "getConfigPropertiesByApplicationAndProfile", query = "SELECT cp FROM ConfigProperty cp WHERE cp.application =:application AND cp.profile=:profile AND cp.isActive = true")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "configProperty")
public class ConfigProperty implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    @Builder.Default
    protected Boolean isActive = true;
    private String application;
    private String profile;
    private String label;
    private String configKey;
    private String configValue;

}
