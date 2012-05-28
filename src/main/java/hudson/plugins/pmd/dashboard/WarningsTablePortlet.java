package hudson.plugins.pmd.dashboard;

import hudson.Extension;
import hudson.model.Descriptor;
import hudson.plugins.analysis.core.AbstractProjectAction;
import hudson.plugins.analysis.dashboard.AbstractWarningsTablePortlet;
import hudson.plugins.pmd.Messages;
import hudson.plugins.pmd.PmdProjectAction;
import hudson.plugins.view.dashboard.DashboardPortlet;

import org.kohsuke.stapler.DataBoundConstructor;

/**
 * A portlet that shows a table with the number of warnings in a job.
 *
 * @author Ulli Hafner
 */
public class WarningsTablePortlet extends AbstractWarningsTablePortlet {
    /**
     * Creates a new instance of {@link WarningsTablePortlet}.
     *
     * @param name
     *            the name of the portlet
     * @param canHideZeroWarningsProjects
     *            determines if zero warnings projects should be hidden in the
     *            table
     */
    @DataBoundConstructor
    public WarningsTablePortlet(final String name, final boolean canHideZeroWarningsProjects) {
        super(name, canHideZeroWarningsProjects);
    }

    @Override
    protected Class<? extends AbstractProjectAction<?>> getAction() {
        return PmdProjectAction.class;
    }

    /**
     * Extension point registration.
     *
     * @author Ulli Hafner
     */
    @Extension(optional = true)
    public static class WarningsPerJobDescriptor extends Descriptor<DashboardPortlet> {
        @Override
        public String getDisplayName() {
            return Messages.Portlet_WarningsTable();
        }
    }
}

