package codegen;
class MakerCodeGenTemplateHolder {

    static def propertiesTemplate = '''<% properties.each { %>public static final Property<$className,$it.fieldDataType> $it.fieldName = newProperty();
                        <% } %>'''

    static def instantiatorTemplate = ''' public static final Instantiator<$className> $objectName = new Instantiator<$className>(){
    @Override
    public $className instantiate(PropertyLookup<$className> lookup) {
     $className $objectName = new $className();
     <% properties.each { %>$objectName.$it.setterName(lookup.valueOf($it.fieldName,null /*Put a value here*/));
                        <% } %>
    return $objectName;
    }
};
'''
}

