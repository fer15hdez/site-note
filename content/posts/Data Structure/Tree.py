# Python3 program to for tree traversals


# A class that represents an individual node in a
# Binary Tree
class Node:
	def __init__(self, key):
		self.left = None
		self.right = None
		self.val = key


# A function to do inorder tree traversal
def printInorder(root):

	if root:

		# First recur on left child
		printInorder(root.left)

		# Then print the data of node
		print(root.val, end=" "),

		# Now recur on right child
		printInorder(root.right)


# Driver code
if __name__ == "__main__":
	root = Node(1)
	root.left = Node(2)
	root.right = Node(3)
	root.left.left = Node(4)
	root.left.right = Node(5)

	# Function call
	# print("Inorder traversal of binary tree is")
	# printInorder(root)

# OUTPUT
# Inorder traversal of binary tree is 
# 4 2 5 1 3 

# ---------------------------------------------------------------------------------------

# Python3 program to for tree traversals


# A class that represents an individual node
# in a Binary Tree
class Node:
	def __init__(self, key):
		self.left = None
		self.right = None
		self.val = key


# A function to do preorder tree traversal
def printPreorder(root):

	if root:

		# First print the data of node
		print(root.val, end=" "),

		# Then recur on left child
		printPreorder(root.left)

		# Finally recur on right child
		printPreorder(root.right)


# Driver code
if __name__ == "__main__":
	root = Node(1)
	root.left = Node(2)
	root.right = Node(3)
	root.left.left = Node(4)
	root.left.right = Node(5)

	# Function call
	# print("Preorder traversal of binary tree is")
	# printPreorder(root)

#  OUTPUT
# Preorder traversal of binary tree is 
# 1 2 4 5 3 

# -----------------------------------------------------------------------------------------------
# Python3 program to for tree traversals


# A class that represents an individual node
# in a Binary Tree
class Node:
	def __init__(self, key):
		self.left = None
		self.right = None
		self.val = key


# A function to do postorder tree traversal
def printPostorder(root):

	if root:

		# First recur on left child
		printPostorder(root.left)

		# The recur on right child
		printPostorder(root.right)

		# Now print the data of node
		print(root.val, end=" "),


# Driver code
if __name__ == "__main__":
	root = Node(1)
	root.left = Node(2)
	root.right = Node(3)
	root.left.left = Node(4)
	root.left.right = Node(5)

	# Function call
	# print("Postorder traversal of binary tree is")
	# printPostorder(root)

# OUTPUT
# Postorder traversal of binary tree is 
# 4 5 2 3 1 


# -----------------------------------------------------------------------------------------------


# Inorder traversal using Morris Traversal:

# Following is the algorithm to implement inorder traversal using Morris traversal:

#     Initialize the current node as root.
#     While current is not null, check if it has a left child.
#         If there is no left child, print the current node and move to the right child of the current node.
#         Otherwise, find the rightmost node of the left subtree or the node whose right child is the current node:
#             If the right child is NULL, make current as the right child and move to the left child of current.
#             If the right child is the current node itself, print current node, make the right child NULL and move to the right child of the current node.


class TreeNode:
    def __init__(self, data):
        self.data = data
        self.left = None
        self.right = None
 
 
def morris_traversal(root):
    current = root
 
    while current:
        if current.left is None:
            print(current.data, end=" ")
            current = current.right
        else:
            # Find the inorder predecessor of current
            pre = current.left
            while pre.right and pre.right != current:
                pre = pre.right
 
            # Make current as the right child of its inorder predecessor
            if pre.right is None:
                pre.right = current
                current = current.left
            # Revert the changes made to restore the original tree and print current node
            else:
                pre.right = None
                print(current.data, end=" ")
                current = current.right
 
 
# Driver program to test above functions
if __name__ == '__main__':
    """
    Constructed binary tree is
            1
          /   \
         2     3
       /   \
      4     5
    """
    root = TreeNode(1)
    root.left = TreeNode(2)
    root.right = TreeNode(3)
    root.left.left = TreeNode(4)
    root.left.right = TreeNode(5)

    root.right.left = TreeNode(6)
    root.right.right = TreeNode(7)
 
    morris_traversal(root)
    # 4 2 5 1 3
