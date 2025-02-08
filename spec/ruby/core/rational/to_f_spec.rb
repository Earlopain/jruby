require_relative "../../spec_helper"

describe "Rational#to_f" do
  it "returns self converted to a Float" do
    Rational(3, 4).to_f.should eql(0.75)
    Rational(3, -4).to_f.should eql(-0.75)
    Rational(-1, 4).to_f.should eql(-0.25)
    Rational(-1, -4).to_f.should eql(0.25)
  end

  it "converts to a Float for large numerator and denominator" do
    num = 1000000000000000000000000000000000048148248609680896326399448564623182963452541226153892315137780403285956264146010000000000000000000000000000000000048148248609680896326399448564623182963452541226153892315137780403285956264146010000000000000000000000000000000000048148248609680896326399448564623182963452541226153892315137780403285956264146009
    den = 2000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000
    Rational(num, den).to_f.should == 500.0
  end
end
