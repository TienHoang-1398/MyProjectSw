module FinalProject {
	requires java.xml;
	requires com.google.gson;
	opens com.example.server.Models to com.google.gson;
}