exclude :"test_thread_size:ant", "requires rake and test runs without rake available"
exclude :"test_thread_size:jar_install_post_install_hook", "requires gems and test runs without gems"
exclude :"test_thread_size:jars/gemspec_pom", "not meant to be loaded on its own"
exclude :"test_thread_size:jars/lock_down_pom", "not meant to be loaded on its own"
exclude :"test_thread_size:jars/output_jars_pom", "not meant to be loaded on its own"
exclude :"test_thread_size:jars/post_install_hook", "requires gems and test runs without gems"
exclude :"test_thread_size:psych_jars", "not meant to be loaded on its own and tries to load psych but test runs without psych available"
exclude :"test_thread_size:rake/ant", "requires rake and test runs without rake available"
exclude :"test_thread_size:win32/registry", "win32 not available on unix but we ship the library by default"
exclude :"test_thread_size:win32/resolv", "win32 not available on unix but we ship the library by default"
exclude :"test_thread_size:win32api", "win32api not available on unix but we ship the library by default"
exclude :"test_thread_size:win32ole", "win32ole not available on unix but we ship the library by default"
