This is a temporary repo based on karibu10-helloworld-application to highlight a build problem.

#build and start:

./gradlew -Pvaadin.productionMode=true clean build

unzip build/distributions/vaadin-naughty-dep.zip /tmp/

/tmp/vaadin-naughty-dep/bin/vaadin-naughty-dep

Started in PT0.735S. Running on Java JetBrains s.r.o. 21, OS aarch64 Mac OS X 14.4 Please open http://localhost:8080/ in your browser.
Press ENTER or CTRL+C to shutdown

#browse:

INFO com.vaadin.flow.server.DefaultDeploymentConfiguration - Vaadin is running in production mode.

ERROR com.vaadin.flow.component.internal.UIInternals - The component class org.vaddon.ElementMediaQuery includes './element-media-query.js' but this file was not included when creating the production bundle. The component will not work properly. Check that you have a reference to the component and that you are not using it only through reflection. If needed add a @Uses(ElementMediaQuery.class) where it is used.
