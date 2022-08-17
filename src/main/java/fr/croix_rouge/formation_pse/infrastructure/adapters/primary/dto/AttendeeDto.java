package fr.croix_rouge.formation_pse.infrastructure.adapters.primary.dto;

import fr.croix_rouge.formation_pse.domain.Attendee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
public class AttendeeDto {
  private Long id;
  private String firstName;
  private String lastName;

  public static AttendeeDto fromDomain(Attendee attendee) {
    return AttendeeDto.builder()
      .id(attendee.getId())
      .firstName(attendee.getFirstName())
      .lastName(attendee.getLastName())
      .build();
  }

  public static Attendee toDomain(AttendeeDto attendee) {
    return Attendee.builder()
      .id(attendee.getId())
      .firstName(attendee.getFirstName())
      .lastName(attendee.getLastName())
      .build();
  }
}
