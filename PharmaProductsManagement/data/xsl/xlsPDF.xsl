<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
		xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo">
 <xsl:template match="/">
<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
  <fo:layout-master-set>
    <fo:simple-page-master  master-name="A4-landscape" page-height="21cm" page-width="29.7cm">
      <fo:region-body margin="0.2in"/>
    </fo:simple-page-master>
  </fo:layout-master-set>

  <fo:page-sequence master-reference="A4-landscape">
    <fo:flow flow-name="xsl-region-body">
     <fo:block text-align ="center"   margin-bottom = "20px"     margin-right ="1px"
    		   margin-left = "1px" background-color="#4CAF50" font-weight="bold"  font-size ="300%"
    		   color="white" padding = "19">Product Inventory</fo:block>
      <fo:block>
      	<fo:table table-layout="fixed" width="100%">
      		 
			 <fo:table-header  background-color="#4CAF50" color="white">
				<fo:table-cell border = "ridge" text-align ="center" >
					<fo:block font-weight="bold">Product Name</fo:block>
				</fo:table-cell>
				
				<fo:table-cell border = "ridge" text-align ="center" >
					<fo:block font-weight="bold">Price</fo:block>
				</fo:table-cell>
				
				<fo:table-cell border = "ridge" text-align ="center" >
					<fo:block font-weight="bold">Quantity</fo:block>
				</fo:table-cell>
				
				<fo:table-cell border = "ridge" text-align ="center" >
					<fo:block font-weight="bold">Company Name</fo:block>
				</fo:table-cell>
				
				<fo:table-cell border = "ridge" text-align ="center" >
					<fo:block font-weight="bold">Category</fo:block>
				</fo:table-cell>
				
				
				<fo:table-cell border = "ridge" text-align ="center">
					<fo:block font-weight="bold">Company Address</fo:block>
				</fo:table-cell>
				
				<fo:table-cell border = "ridge" text-align ="center">
					<fo:block font-weight="bold">Exp. Date</fo:block>
				</fo:table-cell>
				
			</fo:table-header>
			 
			 <fo:table-body>
				<xsl:for-each select="//Product">
					<fo:table-row>
					
				       
									<fo:table-cell border="solid 1px black" text-align="center">
										<fo:block>
											<xsl:value-of select="ProductName"/>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell border="solid 1px black" text-align="center">
										<fo:block>
											<xsl:value-of select="ProductPrice"/>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell border="solid 1px black" text-align="center">
										<fo:block>
											<xsl:value-of select="ProductQuantity"/>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell border="solid 1px black" text-align="center">
										<fo:block>
											<xsl:value-of select="Category/Company/CompanyName"/>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell border="solid 1px black" text-align="center">
										<fo:block>
											<xsl:value-of select="Category/CategoryName"/>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell border="solid 1px black" text-align="center">
										<fo:block>
											<xsl:value-of select="Category/Company/CompanyAddress"/>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell border="solid 1px black" text-align="center">
										<fo:block>
											<xsl:value-of select="ExpDate"/>
										</fo:block>
									</fo:table-cell>
						
					</fo:table-row>
				</xsl:for-each>
      		 </fo:table-body>
      	</fo:table>
      </fo:block>
    </fo:flow>
  </fo:page-sequence>
</fo:root>
</xsl:template>
</xsl:stylesheet>
