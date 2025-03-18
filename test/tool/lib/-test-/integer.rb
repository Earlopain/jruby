require 'test/unit'
require 'jruby'

module Test::Unit::Assertions
  def assert_fixnum(v, msg=nil)
    assert_instance_of(Integer, v, msg)
    assert_send([Bug::Integer, :fixnum?, v], msg)
  end

  def assert_bignum(v, msg=nil)
    assert_instance_of(Integer, v, msg)
    assert_send([Bug::Integer, :bignum?, v], msg)
  end
end

module Bug
  class Integer
    def self.to_bignum(i)
      org.jruby.RubyBignum.newBignum(JRuby.runtime, i);
    end
  end
end