/*
 ***** BEGIN LICENSE BLOCK *****
 * Version: EPL 2.0/GPL 2.0/LGPL 2.1
 *
 * The contents of this file are subject to the Eclipse Public
 * License Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of
 * the License at http://www.eclipse.org/legal/epl-v20.html
 *
 * Software distributed under the License is distributed on an "AS
 * IS" basis, WITHOUT WARRANTY OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * rights and limitations under the License.
 *
 * Copyright (C) 2002 Jan Arne Petersen <jpetersen@uni-bonn.de>
 * Copyright (C) 2002 Benoit Cerrina <b.cerrina@wanadoo.fr>
 * Copyright (C) 2002-2004 Anders Bengtsson <ndrsbngtssn@yahoo.se>
 * Copyright (C) 2004 Thomas E Enebo <enebo@acm.org>
 * 
 * Alternatively, the contents of this file may be used under the terms of
 * either of the GNU General Public License Version 2 or later (the "GPL"),
 * or the GNU Lesser General Public License Version 2.1 or later (the "LGPL"),
 * in which case the provisions of the GPL or the LGPL are applicable instead
 * of those above. If you wish to allow use of your version of this file only
 * under the terms of either the GPL or the LGPL, and not to allow others to
 * use your version of this file under the terms of the EPL, indicate your
 * decision by deleting the provisions above and replace them with the notice
 * and other provisions required by the GPL or the LGPL. If you do not delete
 * the provisions above, a recipient may use your version of this file under
 * the terms of any one of the EPL, the GPL or the LGPL.
 ***** END LICENSE BLOCK *****/

package org.jruby.ast;

import java.util.List;

import org.jruby.RubySymbol;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.util.ByteList;
import org.jruby.util.CommonByteLists;

public class OpAsgnNode extends Node {
    private final Node receiverNode;
    private final Node valueNode;
    private final RubySymbol variableName;
    private final RubySymbol operatorName;
    private final RubySymbol variableNameAsgn;
    private final boolean isLazy;

    public OpAsgnNode(int line, Node receiverNode, Node valueNode, RubySymbol variableName,
                      RubySymbol operatorName, boolean isLazy) {
        super(line, receiverNode.containsVariableAssignment());

        assert valueNode != null : "valueNode is not null";

        this.receiverNode = receiverNode;
        this.valueNode = valueNode;
        this.variableName = variableName;
        this.operatorName = operatorName;
        this.variableNameAsgn = variableName.asWriter();
        this.isLazy = isLazy;
    }

    public NodeType getNodeType() {
        return NodeType.OPASGNNODE;
    }

    /**
     * Accept for the visitor pattern.
     * @param iVisitor the visitor
     **/
    public <T> T accept(NodeVisitor<T> iVisitor) {
        return iVisitor.visitOpAsgnNode(this);
    }

    /**
     * Gets the methodName.
     * @return Returns a String
     */
    public String getOperatorName() {
        return operatorName.asJavaString();
    }

    public ByteList getOperatorByteName() {
        return operatorName.getBytes();
    }

    public RubySymbol getOperatorSymbolName() {
        return operatorName;
    }

    public boolean isOr() {
        return CommonByteLists.OR_OR.equals(operatorName.getBytes());
    }

    public boolean isAnd() {
        return CommonByteLists.AMPERSAND_AMPERSAND.equals(operatorName.getBytes());
    }

    /**
     * Gets the receiverNode.
     * @return Returns a Node
     */
    public Node getReceiverNode() {
        return receiverNode;
    }

    /**
     * Gets the valueNode.
     * @return Returns a Node
     */
    public Node getValueNode() {
        return valueNode;
    }

    /**
     * Gets the varibaleName.
     * @return Returns a String
     */
    public String getVariableName() {
        return variableName.asJavaString();
    }

    public ByteList getVariableByteName() {
        return variableName.getBytes();
    }

    public RubySymbol getVariableSymbolName() {
        return variableName;
    }
    
    public String getVariableNameAsgn() {
        return variableNameAsgn.asJavaString();
    }

    public ByteList getVariableByteNameAsgn() {
        return variableNameAsgn.getBytes();
    }

    public RubySymbol getVariableSymbolNameAsgn() {
        return variableNameAsgn;
    }
    
    public List<Node> childNodes() {
        return Node.createList(receiverNode, valueNode);
    }

    public boolean isLazy() {
        return isLazy;
    }

    @Override
    public boolean needsDefinitionCheck() {
        return false;
    }
}
