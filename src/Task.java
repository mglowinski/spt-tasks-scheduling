import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class Task {

    private int number;
    private int processingTime;
}
