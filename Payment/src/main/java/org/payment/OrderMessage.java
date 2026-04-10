package org.payment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderMessage {

    private Payload payload;
    private Schema schema;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Payload {
        private Before before;
        private After after;
        private Source source;
        private Object transaction;
        private String op;
        @JsonProperty("ts_ms")
        private long tsMs;
        @JsonProperty("ts_us")
        private long tsUs;
        @JsonProperty("ts_ns")
        private long tsNs;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class After {
        public Long id;
        @JsonProperty("created_at")
        public Object createdAt;
        @JsonProperty("customer_id")
        public Long customerId;
        public String price;
        @JsonProperty("product_id")
        public Long productId;
        @JsonProperty("updated_at")
        public Object updatedAt;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Before {
        public Long id;
        @JsonProperty("created_at")
        public Object createdAt;
        @JsonProperty("customer_id")
        public Long customerId;
        public String price;
        @JsonProperty("product_id")
        public Long productId;
        @JsonProperty("updated_at")
        public Object updatedAt;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Source {
        public String version;
        public String connector;
        public String name;
        @JsonProperty("ts_ms")
        public long tsMs;
        public String snapshot;
        public String db;
        public Object sequence;
        @JsonProperty("ts_us")
        public long tsUs;
        @JsonProperty("ts_ns")
        public long tsNs;
        public String table;
        @JsonProperty("server_id")
        public int serverId;
        public String gtid;
        public String file;
        public int pos;
        public int row;
        public int thread;
        public Object query;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Schema {
        public String type;
        public List<Field> fields;
        public boolean optional;
        public String name;
        public int version;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Field {
        public String type;
        public List<Field> fields;
        public boolean optional;
        public String name;
        public String field;
        public int version;
        public Parameters parameters;
        @JsonProperty("default")
        public String myDefault;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Parameters {
        public String scale;
        @JsonProperty("connect.decimal.precision")
        public String connectDecimalPrecision;
        public String allowed;
    }
}