# Transactional Outbox Pattern (Spring Boot)

An example implementation of the **Transactional Outbox Pattern** using **Spring Boot**, **MySQL**, **Debezium**, **Kafka**, and Kubernetes local tooling (**Minikube** + **Skaffold**).

The goal is to reliably publish domain events to Kafka *without* dual-writing to the database and the message broker. Events are first written to an outbox table in the same database transaction as the business data; Debezium then streams outbox changes to Kafka.

## Tech Stack

- **Spring Boot** (Java)
- **MySQL**
- **Kafka**
- **Debezium** (CDC from MySQL to Kafka)
- **Minikube** (local Kubernetes)
- **Skaffold** (build & deploy loop)

## Architecture (High Level)

1. Application writes business data **and** an outbox record in the **same DB transaction**.
2. **Debezium MySQL connector** captures changes from MySQL binlog.
3. Debezium publishes change events to **Kafka topics**.
4. Consumers read from Kafka and process events downstream.

## Prerequisites

- Java 17+ (or your project’s configured Java version)
- Docker
- Minikube
- Skaffold
- kubectl

> If you run everything with Docker Compose instead of Kubernetes, you can still use this project, but the commands below assume **Minikube + Skaffold**.

## Local Development

### 1) Start Minikube

```bash
minikube start
```

### 2) Deploy the stack with Skaffold

From the repository root:

```bash
skaffold dev
```

Skaffold will build images and deploy to the Minikube cluster, then watch for code changes.

### 3) Verify pods

```bash
kubectl get pods
```

### 4) (Optional) Port-forward services

Adjust service names/ports to match what’s defined in your manifests.

```bash
kubectl port-forward svc/kafka 9092:9092
kubectl port-forward svc/mysql 3306:3306
```

## Kafka & Debezium Notes

- Debezium requires MySQL binlog enabled and a user with replication privileges.
- The Debezium connector configuration determines which tables are captured and where events land in Kafka.
- If you use the outbox pattern with Debezium, you’ll typically either:
  - publish raw outbox table row changes, or
  - use Debezium’s **Outbox Event Router** SMT to route events by aggregate/type.

## Project Structure

This README is intentionally generic. Once your repo structure stabilizes, consider documenting:

- API endpoints
- Database schema (business + outbox tables)
- Connector configuration files
- Kubernetes manifests / Helm charts

## Useful Commands

```bash
# View logs for a deployment
kubectl logs -l app=<app-label> -f

# Restart a deployment
kubectl rollout restart deployment/<deployment-name>

# Check Kafka topics (example if you have a tooling pod)
kubectl exec -it <kafka-pod> -- bash
```
