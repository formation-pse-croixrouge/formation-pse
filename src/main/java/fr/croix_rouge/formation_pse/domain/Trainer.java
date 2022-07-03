package fr.croix_rouge.formation_pse.domain;

import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Builder
@Getter
public class Trainer {

  private final Long id;
  private final String nivol;
  private final String firstName;
  private final String lastName;

  public Trainer(Long id, String nivol, String firstName, String lastName) {
    validate(nivol, firstName, lastName);
    this.id = id;
    this.nivol = nivol;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  private void validate(String nivol, String firstName, String lastName) {
    if(StringUtils.isBlank(nivol)) {
      throw new IllegalArgumentException("Nivol cannot be blank");
    }
    if(StringUtils.isBlank(firstName)) {
      throw new IllegalArgumentException("FirstName cannot be blank");
    }
    if(StringUtils.isBlank(lastName)) {
      throw new IllegalArgumentException("LastName cannot be blank");
    }
  }
}
