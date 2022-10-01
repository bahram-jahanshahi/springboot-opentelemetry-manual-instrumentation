package se.bahram.opentelemetry;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.StatusCode;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import se.bahram.opentelemetry.configs.OpenTelemetryConfig;

@SpringBootApplication
public class SpringbootOpentelemetryManualInstrumentationApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootOpentelemetryManualInstrumentationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		OpenTelemetrySdk openTelemetrySdk = OpenTelemetryConfig.setup();

		Tracer tracer = openTelemetrySdk.getTracer("MainClass");
		Span mySpan = tracer.spanBuilder("My Span").startSpan();
		mySpan.setAttribute("Attribute 1", "Value 1");
		mySpan.setAttribute("Attribute 2", "Value 2");
		mySpan.setStatus(StatusCode.OK);
		mySpan.end();
	}
}
