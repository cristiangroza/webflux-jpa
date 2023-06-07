package com.jpamigration.jpamigration;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "com.jpamigration")
public class BlockingRepositoryArchTest {

    @ArchTest
     public static final ArchRule packagePrivateCrudRepository = classes().that()
                .areInterfaces()
                .and()
                .areAssignableTo(CrudRepository.class)
                .should()
                .bePackagePrivate();

    @ArchTest
    public static final ArchRule blockingInterfacesNaming = classes().that()
            .areInterfaces()
            .and()
            .areAssignableTo(CrudRepository.class)
            .should()
            .haveSimpleNameEndingWith("JpaRepository");

    @ArchTest
    public static final ArchRule blockingInterfaceUsageClassName = classes().that()
            .areInterfaces()
            .and()
            .areAssignableTo(CrudRepository.class)
            .should()
            .onlyBeAccessed()
            .byClassesThat()
            .haveSimpleNameEndingWith("ReactiveRepository");

    @ArchTest
    public static final ArchRule blockingInterfaceUsageAnnotations = classes().that()
            .areInterfaces()
            .and()
            .areAssignableTo(CrudRepository.class)
            .should()
            .onlyBeAccessed()
            .byClassesThat()
            .areAnnotatedWith(Repository.class);

}
