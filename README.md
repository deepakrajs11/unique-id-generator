# 🚀 Unique ID Generator

A lightweight and scalable **Spring Boot microservice** that generates globally unique, time-ordered IDs using the **Twitter Snowflake Algorithm**.

Instead of relying on database auto-increment or random UUIDs, this project generates distributed unique IDs that are:

- 🌍 Globally Unique
- ⚡ High Performance
- 📈 Time Ordered
- 🔥 Database Friendly
- ☁️ Suitable for Distributed Systems

---

# Why Twitter Snowflake?

Traditional ID generation techniques have limitations in distributed systems.

| Approach | Problem |
|----------|----------|
| Auto Increment | Doesn't work across multiple databases |
| UUID | Large (128-bit), random, poor DB indexing |
| Centralized ID Server | Single Point of Failure |

Twitter Snowflake solves these problems by generating IDs independently on every server without requiring any centralized coordination.

---

# Workflow

```text
                 Client
                    │
                    ▼
          GET /api/v1/ids
                    │
                    ▼
             IdController
                    │
                    ▼
           UniqueIdService
                    │
                    ▼
        UniqueIdGenerator
                    │
                    ▼
   TwitterSnowflakeGenerator
                    │
     ┌──────────────┴──────────────┐
     │                             │
     ▼                             ▼
 TimeProvider              Generator Properties
     │
     ▼
System.currentTimeMillis()
                    │
                    ▼
          Generate Snowflake ID
                    │
                    ▼
            Return 64-bit Long
```

---

# Snowflake Algorithm

Each generated ID consists of **64 bits**.

```text
┌────┬──────────────────────────┬──────────┬────────┬────────────┐
│ 1  │       41 Bits            │ 5 Bits   │ 5 Bits │ 12 Bits    │
├────┼──────────────────────────┼──────────┼────────┼────────────┤
│Sign│ Timestamp                │Datacenter│ Worker │ Sequence   │
└────┴──────────────────────────┴──────────┴────────┴────────────┘
```

### Bit Allocation

| Component | Bits | Description |
|-----------|------|-------------|
| Sign | 1 | Always 0 |
| Timestamp | 41 | Milliseconds since Custom Epoch |
| Datacenter | 5 | Supports up to 32 Datacenters |
| Worker | 5 | Supports up to 32 Workers per Datacenter |
| Sequence | 12 | Supports 4096 IDs per millisecond |

---

# Algorithm Flow

```text
Receive Request
      │
      ▼
Get Current Timestamp
      │
      ▼
Clock Moved Backwards?
      │
 ┌────┴────┐
 │         │
Yes        No
 │          │
Throw      Same Millisecond?
Exception      │
          ┌────┴────┐
          │         │
         Yes        No
          │          │
Increment   Reset Sequence
Sequence         │
          │       │
Sequence Overflow?
          │
     ┌────┴─────┐
     │          │
    Yes         No
     │
Wait Until Next Millisecond
     │
     ▼
Combine Timestamp + Datacenter + Worker + Sequence
     │
     ▼
Return Unique ID
```

---

# Project Structure

```text
src
└── main
    ├── config
    ├── controller
    ├── dto
    ├── generator
    ├── properties
    └── service
```

### Package Responsibilities

| Package | Responsibility |
|----------|----------------|
| config | Spring Bean Configuration |
| controller | REST Endpoints |
| dto | API Response Models |
| generator | Snowflake Algorithm |
| properties | Configuration Binding |
| service | Business Logic |

---

# REST API

## Generate ID

```http
GET /api/v1/ids
```

Response

```json
{
    "id": 332152529670508544
}
```

---

# Configuration

```yaml
id-generator:
  worker-id: 1
  datacenter-id: 1
  custom-epoch: 1704067200000
```

---

# Run Locally

Clone the repository

```bash
git clone https://github.com/deepakrajs11/unique-id-generator.git
```

Navigate to the project

```bash
cd unique-id-generator
```

Run the application

```bash
./mvnw spring-boot:run
```

Application starts on

```
http://localhost:8080
```

---

# Tech Stack

- Java 21
- Spring Boot 3
- Maven
- REST API

---

# Future Enhancements

- Snowflake ID Decoder API
- UUID v7 Support
- ULID Support
- Docker
- Kubernetes Deployment
- Unit & Integration Tests
- Benchmarking

---

# Learn More

📖 Medium Article

**From Auto-Increment IDs to Twitter Snowflake: Building a Scalable Unique ID Generator with Spring Boot**

https://medium.com/@deepakrajs1103/from-auto-increment-ids-to-twitter-snowflake-building-a-scalable-unique-id-generator-with-spring-e7c9fc5111f3

---

## ⭐ If you found this project useful, consider giving it a star!