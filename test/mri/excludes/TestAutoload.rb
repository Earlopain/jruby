exclude :test_autoload_deprecate_constant, "to be fixed in #5489"
exclude :test_autoload_deprecate_constant_before_autoload, "work in progress"
exclude :test_autoload_parallel_race, "hangs"
exclude :test_autoload_parent_namespace, "expected constant does not get defined"
exclude :test_autoload_private_constant, "work in progress"
exclude :test_autoload_private_constant_before_autoload, "work in progress"
exclude :test_autoload_same_file, "to be fixed in #5489"
exclude :test_bug_13526, "racey test that doesn't behave as expected with concurrent threads #5294"
exclude :test_no_memory_leak, "no working assert_no_memory_leak method"
exclude :test_source_location, "mismatched path canonicalization"
exclude :test_source_location_after_require, "mismatched path canonicalization"
