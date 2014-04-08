package dashboard.rest;

import dashboard.domain.jenkins.Job;
import dashboard.domain.jenkins.Jobs;
import dashboard.jenkins.api.JenkinsApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import restx.annotations.GET;
import restx.annotations.Produces;
import restx.annotations.RestxResource;
import restx.factory.Component;
import restx.security.PermitAll;

@Component @RestxResource
public class JobsResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobsResource.class);

    private final JenkinsApi jenkinsApi;

    public JobsResource(JenkinsApi jenkinsApi) {
        this.jenkinsApi = jenkinsApi;
    }

    @GET("/jobs")
    @PermitAll
    public Jobs getJobs() {
        Jobs jobs = jenkinsApi.getJobs();
        LOGGER.info("Jobs : {}", jobs);
        return jobs;
    }

    @GET("/job/{jobName}")
    @Produces("application/json;view=dashboard.Views$Details")
    @PermitAll
    public Job getJob(String jobName) {
        Job job = jenkinsApi.getJob(jobName);
        LOGGER.info("Job : {}", job);
        return job;
    }

}
