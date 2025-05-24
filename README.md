Getting Started
1. Clone the Repository
bash
git clone https://github.com/your-username/simple-crud-logs-visualization.git
cd simple-crud-logs-visualization
2. Build the Application
Build the Spring Boot application JAR file (if required):

bash
./mvnw clean package
3. Start the Application
Use Docker Compose to start all services:

bash
docker compose up -d
4. Access the Services
Spring Boot Application: http://localhost:8080

Prometheus: http://localhost:9090

Grafana: http://localhost:3000
Default credentials: admin / admin

Loki: http://localhost:3100

Configuration
Environment Variables
Configure the application using the following environment variables in the docker-compose.yaml file:

SPRING_DATASOURCE_URL: Database connection URL

SPRING_DATASOURCE_USERNAME: Database username

SPRING_DATASOURCE_PASSWORD: Database password

Prometheus
Prometheus scrapes metrics from:

Spring Boot: /actuator/prometheus

Loki: http://loki:3100

Grafana
Grafana is pre-configured to use Prometheus and Loki as data sources. You can add dashboards for monitoring metrics and logs.

Logs
Promtail collects logs from the Spring Boot application and pushes them to Loki. Logs are stored in /tmp/Condensr.log.

Troubleshooting
Common Issues
Database Connection Error

Ensure the PostgreSQL container is running and accessible.

Verify SPRING_DATASOURCE_URL, SPRING_DATASOURCE_USERNAME, and SPRING_DATASOURCE_PASSWORD values.

No Logs in Grafana

Ensure Promtail is configured correctly and the log file path is mounted properly.

Check Promtail and Loki logs for errors.

Metrics Not Visible

Verify Prometheus is scraping the correct endpoints.

Check the Prometheus configuration file (prometheus.yaml).

License
This project is licensed under the MIT License. See the LICENSE file for details.

Contributing
Contributions are welcome! Please open an issue or submit a pull request for any improvements or bug fixes.

Contact
For any questions or support, please contact: utkarsh.shankar100@gmail.com
