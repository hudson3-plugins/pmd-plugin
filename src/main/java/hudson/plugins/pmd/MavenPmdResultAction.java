package hudson.plugins.pmd;

import hudson.maven.AggregatableAction;
import hudson.maven.MavenAggregatedReport;
import hudson.maven.MavenBuild;
import hudson.maven.MavenModule;
import hudson.maven.MavenModuleSet;
import hudson.maven.MavenModuleSetBuild;
import hudson.model.Action;
import hudson.model.AbstractBuild;
import hudson.plugins.analysis.core.HealthDescriptor;
import hudson.plugins.analysis.core.ParserResult;

import java.util.List;
import java.util.Map;

/**
 * A {@link PmdResultAction} for native maven jobs. This action
 * additionally provides result aggregation for sub-modules and for the main
 * project.
 *
 * @author Ulli Hafner
 * @deprecated not used anymore
 */
@Deprecated
public class MavenPmdResultAction extends PmdResultAction implements AggregatableAction, MavenAggregatedReport {
    /** The default encoding to be used when reading and parsing files. */
    private final String defaultEncoding;

    /**
     * Creates a new instance of {@link MavenPmdResultAction}.
     *
     * @param owner
     *            the associated build of this action
     * @param healthDescriptor
     *            health descriptor to use
     * @param defaultEncoding
     *            the default encoding to be used when reading and parsing files
     * @param result
     *            the result in this build
     */
    public MavenPmdResultAction(final AbstractBuild<?, ?> owner, final HealthDescriptor healthDescriptor,
            final String defaultEncoding, final PmdResult result) {
        super(owner, healthDescriptor, result);

        this.defaultEncoding = defaultEncoding;
    }

    /** {@inheritDoc} */
    public MavenAggregatedReport createAggregatedAction(final MavenModuleSetBuild build, final Map<MavenModule, List<MavenBuild>> moduleBuilds) {
        return new MavenPmdResultAction(build, getHealthDescriptor(), defaultEncoding,
                new PmdResult(build, defaultEncoding, new ParserResult(), false));
    }

    /** {@inheritDoc} */
    public Action getProjectAction(final MavenModuleSet moduleSet) {
        return new PmdProjectAction(moduleSet);
    }

    /** {@inheritDoc} */
    public Class<? extends AggregatableAction> getIndividualActionType() {
        return getClass();
    }

    /**
     * Called whenever a new module build is completed, to update the aggregated
     * report. When multiple builds complete simultaneously, Hudson serializes
     * the execution of this method, so this method needs not be
     * concurrency-safe.
     *
     * @param moduleBuilds
     *            Same as <tt>MavenModuleSet.getModuleBuilds()</tt> but provided
     *            for convenience and efficiency.
     * @param newBuild
     *            Newly completed build.
     */
    public void update(final Map<MavenModule, List<MavenBuild>> moduleBuilds, final MavenBuild newBuild) {
        // not used anymore
    }

    /** Backward compatibility. @deprecated */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings("UUF")
    @SuppressWarnings("PMD")
    @Deprecated
    private transient String height;
}

