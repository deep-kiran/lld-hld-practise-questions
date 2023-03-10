package chess.validators;

import chess.models.ValidationProps;


public interface IMoveValidator {
    Boolean validate(ValidationProps props);
}
