package api.petstore.pojo.store;

import api.petstore.utils.DateDeserializer;
import api.petstore.utils.DateSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreOrder {
    public Long id;
    public Long petId;
    public Integer quantity;

    @JsonDeserialize(using = DateDeserializer.class)
    @JsonSerialize(using = DateSerializer.class)
    public OffsetDateTime shipDate;

    public String status;
    public Boolean complete;
}
