package com.deappec.appointment.application.events;

import com.deappec.appointment.domain.models.ShiftAssigned;

public record ShiftAssignedCreatedEvent(ShiftAssigned shiftAssigned) {
}
