exclude :test_classext_memory_leak, "no working assert_no_memory_leak method"
exclude :test_clone_singleton_class_exists, "work in progress"
exclude :test_constant_access_from_method_in_cloned_class, "work in progress"
exclude :test_method_redefinition, "missing a warning"
exclude :test_redefine_private_class, "needs investigation"
exclude :test_redefinition_mismatch, "missing original method location in warning"
exclude :test_singleton_class_should_has_own_namespace, "new behavior for 2.3 (#3693)"
exclude :test_uninitialized, "fails to raise TypeError"
exclude :test_visibility_inside_method, "missing a warning"
